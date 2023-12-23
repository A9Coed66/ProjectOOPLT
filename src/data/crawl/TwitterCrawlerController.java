package data.crawl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithm.GetTweetPostFromJsonFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.post.Tweet;

public class TwitterCrawlerController  {
	
	//**
	//Constructor
	//**
	public TwitterCrawlerController(TableView<Tweet> tblPost, TableColumn<Tweet, String> colContent, TableColumn<Tweet, String> colHashtag,TableColumn<Tweet, String> colTime,TableColumn<Tweet,String> colUserName, Button crawlButton, TextField tfQuery, ToggleGroup filterCategory, RadioButton radioBtnFilterContent) {
		this.tblPost = tblPost;
		this.colContent = colContent;
		this.colHashtag = colHashtag;
		this.colTime = colTime;
		this.colUserName = colUserName;
		this.crawlButton = crawlButton;
		
		this.tfQuery = tfQuery;
		this.filterCategory = filterCategory;
		this.radioBtnFilterContent = radioBtnFilterContent;
	}
	
	//**
	//Initialization
	//**
	
	@FXML 
	void initialize(){
		// Thêm vào phần lọc thời gian
		initializeDateComboBoxes();
		addComboBoxListener(fromMonth);
	    addComboBoxListener(fromDay);
	    addComboBoxListener(fromYear);
		addComboBoxListener(toMonth);
	    addComboBoxListener(toDay);
	    addComboBoxListener(toYear);
		// Chuyển đổi cái lựa chọn trong phần filter
		linkToggle.selectedProperty().addListener( new ChangeListener<Boolean>() {
    		@Override
    		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    			if(newValue) {
    				postLink.setVisible(true);
    				onlyPostLink.setVisible(false);
    			}
    			else {
    				postLink.setVisible(false);
    				onlyPostLink.setVisible(false);
    			}
    		}
		});
		replyToggle.selectedProperty().addListener( new ChangeListener<Boolean>() {
    		@Override
    		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    			if(newValue) {
    				replyAndPost.setVisible(true);
    				onlyReplies.setVisible(false);
    			}
    			else {
    				replyAndPost.setVisible(false);
    				onlyReplies.setVisible(false);
    			}
    		}
		});
	}

	
	@FXML
	void stop() {
		crawlButton.setDisable(false);
	}
	
	
	//**
	//Action Performed
	//**
	
    @FXML
    void refreshSearchPageButtonPressed(ActionEvent event) {
    	colUserName.setCellValueFactory(new PropertyValueFactory<Tweet, String>("author"));
 	    colTime.setCellValueFactory(new PropertyValueFactory<Tweet, String>("dateCreated"));
 	    colHashtag.setCellValueFactory(new PropertyValueFactory<Tweet, String>("hashtag"));
 	    colContent.setCellValueFactory(cellData -> {
 	    	 String fullContent = cellData.getValue().getContent();
 	    	 String formattedContent = (fullContent.replaceAll("\n", "\\n"));
 	    	        
 	    	 return javafx.beans.binding.Bindings.createObjectBinding(() -> formattedContent);
 	    });
 	    
 	    listItems = new FilteredList<Tweet>(new GetTweetPostFromJsonFile("data/json/post/nitter/tweet_top10collection_timecrawl-20231221_184804.json").jsonReadAlgorithm());
 	    
 	    if(listItems != null ) tblPost.setItems(listItems);
 	    
 	    tfQuery.textProperty().addListener(new ChangeListener<String>(){
 		  			@Override
 		  			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
 		  				showFilteredPost(newValue);
 		  			}

					private void showFilteredPost(String filter) {
						// TODO Auto-generated method stub
						RadioButton selectedButton = (RadioButton)filterCategory.getSelectedToggle();
						if(selectedButton == radioBtnFilterContent) {
							listItems.setPredicate(item -> item.getContent().contains(filter));
						} else {
							listItems.setPredicate(item -> item.getHashtag().contains(filter));
						}
					}
 		          });
 		   
 	    crawlButton.setDisable(false);
 	    Stage currentStage = (Stage) refreshSearchPageButton.getScene().getWindow();
 	    currentStage.close();
    }
    
	public class MonthConverter {
	    private static final Map<String, String> monthMap = new HashMap<>();

	    static {
	        monthMap.put("January", "01");
	        monthMap.put("February", "02");
	        monthMap.put("March", "03");
	        monthMap.put("April", "04");
	        monthMap.put("May", "05");
	        monthMap.put("June", "06");
	        monthMap.put("July", "07");
	        monthMap.put("August", "08");
	        monthMap.put("September", "09");
	        monthMap.put("October", "10");
	        monthMap.put("November", "11");
	        monthMap.put("December", "12");
	    }

	    public static String convertMonthToNumber(String month) {
	        return monthMap.getOrDefault(month, "00");
	    }
	}
    
	
	
	
	 @FXML
	 void btnOkPressed(ActionEvent event) {
		 StringBuffer query= new StringBuffer();
		if(!allWords.getText().isEmpty()) {
			query.append(allWords.getText());
		}
		if(!exactPhrase.getText().isEmpty()) {
			query.append(" \""+exactPhrase.getText()+"\"");
		}
		if(!anyWords.getText().isEmpty()) {
			query.append(" ("+anyWords.getText()+")");
		}
		if(!noWords.getText().isEmpty()) {
			query.append(" -"+noWords.getText());
		}
		if(!hashtags.getText().isEmpty()) {
			query.append(" (#"+hashtags.getText()+")");
		}
		if(!fromAccounts.getText().isEmpty()) {
			query.append(" (from:"+fromAccounts.getText()+")");
		}
		if(!toAccounts.getText().isEmpty()) {
			query.append(" (to:" + toAccounts.getText()+")");
		
		}
		if(!mentionedAccounts.getText().isEmpty()) {
			query.append(" (@"+mentionedAccounts.getText()+")");
			
		}
		if(!minReplies.getText().isEmpty()) {
			query.append(" min_replies:"+minReplies.getText());
		}
		if(!minLikes.getText().isEmpty()) {
			query.append(" min_faves:"+minLikes.getText());
		}
		if(!minReposts.getText().isEmpty()) {
			query.append(" min_retweets:"+minReposts.getText());
		}
	
		if((fromDay.getValue()!= null)&&(fromMonth.getValue()!= null)&&(fromYear.getValue()!= null)) {
			query.append(" since:"+fromYear.getValue()+"-"+MonthConverter.convertMonthToNumber(fromMonth.getValue())+"-"+fromDay.getValue());
		}
		if((toDay.getValue()!= null)&&(toMonth.getValue()!= null)&&(toYear.getValue()!= null)) {
			query.append(" until:"+toYear.getValue()+"-"+MonthConverter.convertMonthToNumber(toMonth.getValue())+"-"+toDay.getValue());
		}
		
		TwitterCrawler.main(query.toString(),Integer.parseInt(postNumber.getText()));;
	}
	 
	    
	    
	 // Cài đặt thời gian cho combobox
	    private void initializeDateComboBoxes() {
	        // Khởi tạo danh sách tháng và năm
	        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December");
	        List<String> days = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
	        List<String> years = Arrays.asList("2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023");

	        // Gán danh sách vào ComboBox
	        fromMonth.setItems(FXCollections.observableArrayList(months));
	        fromDay.setItems(FXCollections.observableArrayList(days));
	        fromYear.setItems(FXCollections.observableArrayList(years));
	        toMonth.setItems(FXCollections.observableArrayList(months));
	        toDay.setItems(FXCollections.observableArrayList(days));
	        toYear.setItems(FXCollections.observableArrayList(years));
	    }

	    private void addComboBoxListener(ComboBox<String> comboBox) {
	        comboBox.setOnAction(event -> handleComboBoxSelection(comboBox));
	    }

	    private void handleComboBoxSelection(ComboBox<String> comboBox) {
	        // Xử lý sự kiện khi một mục được chọn trong ComboBox
	        String selectedValue = comboBox.getValue();
	     
	        // Thêm logic xử lý tùy thuộc vào ComboBox cụ thể
	        switch (comboBox.getId()) {
	            case "fromMonth":
	                handleMonthSelection1(selectedValue);
	                break;
	            case "fromDay":
	                handleDaySelection1(selectedValue);
	                break;
	            case "fromYear":
	                handleYearSelection1(selectedValue);
	            case "toMonth":
	                handleMonthSelection2(selectedValue);
	                break;
	            case "toDay":
	                handleDaySelection2(selectedValue);
	                break;
	            case "toYear":
	                handleYearSelection2(selectedValue);
	                break;
	            // Thêm logic xử lý cho các ComboBox khác tương tự
	            // ...
	        }
	    }

	    private void handleMonthSelection1(String selectedMonth) {
	        // Xử lý khi thay đổi tháng
	    }

	    private void handleDaySelection1(String selectedDay) {
	        // Xử lý khi thay đổi ngày
	    }

	    private void handleYearSelection1(String selectedYear) {
	        // Xử lý khi thay đổi năm
	    }
	    private void handleMonthSelection2(String selectedMonth) {
	        // Xử lý khi thay đổi tháng
//	    	if(selectedMonth.equals("February"));
	    }

	    private void handleDaySelection2(String selectedDay) {
	        // Xử lý khi thay đổi ngày
	    }

	    private void handleYearSelection2(String selectedYear) {
	        // Xử lý khi thay đổi năm
	    }

	@FXML
	private TextField postNumber;
	
    @FXML
    private TextField allWords;

    @FXML
    private TextField anyWords;

    @FXML
    private Button btnOk;

    @FXML
    private TextField exactPhrase;

    @FXML
    private ComboBox<String> fromMonth;

    @FXML
    private TextField fromAccounts;

    @FXML
    private ComboBox<String> fromDay;

    @FXML
    private ComboBox<String> fromYear;

    @FXML
    private TextField hashtags;

    @FXML
    private ToggleGroup linkFilter;

    @FXML
    private ToggleButton linkToggle;

    @FXML
    private TextField mentionedAccounts;

    @FXML
    private TextField minLikes;

    @FXML
    private TextField minReplies;

    @FXML
    private TextField minReposts;

    @FXML
    private TextField noWords;

    @FXML
    private RadioButton onlyPostLink;

    @FXML
    private RadioButton onlyReplies;

    @FXML
    private RadioButton postLink;

    @FXML
    private RadioButton replyAndPost;

    @FXML
    private ToggleGroup replyFilter;

    @FXML
    private ToggleButton replyToggle;

    @FXML
    private TextField toAccounts;

    @FXML
    private ComboBox<String> toDay;

    @FXML
    private ComboBox<String> toMonth;

    @FXML
    private ComboBox<String> toYear;
   
    @FXML
    private Button refreshSearchPageButton;
    
    private TableColumn<Tweet, String> colContent;
    private TableColumn<Tweet, String> colHashtag;
    private TableColumn<Tweet, String> colTime;
    private TableColumn<Tweet,String> colUserName;
    private TableView<Tweet> tblPost;
    private Button crawlButton;
    
    private static FilteredList<Tweet> listItems;
    private TextField tfQuery;
    private ToggleGroup filterCategory;
    private RadioButton radioBtnFilterContent;
}
