$(document).foundation();

$(document).ready(function() {
    var bodyheight = $(window).height();
    $(".windowheight").css('min-height', bodyheight);
    $(window).resize(function() {
        var bodyheight = $(window).height();
        $(".windowheight").css('min-height', bodyheight);
    });
});


var minStartTime, maxStartTime;
function setMinTime(min, max) {
    minStartTime = min;
    maxStartTime = max;
}

var logic = function() {
    this.setOptions({
        minTime: minStartTime,
        maxTime: maxStartTime
    });
};

$('#datetimepicker').datetimepicker({
    datepicker: false,
    format: 'H:i',
    onShow: logic
});

$('#datetimepicker2').datetimepicker({
    datepicker: false,
    format: 'H:i',
});


$(function() {
    $('a[href*=#]:not([href=#])').click(function() {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top
                }, 1000);
                return false;
            }
        }
    });
});

function getHospID(element)
{
    var pressedBtn = element.id;
    var lastChar = pressedBtn;
    document.getElementById("hospID").value = lastChar;
  //  alert("here is " + document.getElementById("hospID").value);
}

function getDocID(element)
{
    var pressedBtn = element.id;
    //var lastChar = pressedBtn.charAt(pressedBtn.length-1);
    var lastChar = pressedBtn;
    document.getElementById("docID").value = lastChar;
    //alert("here is " + document.getElementById("docID").value);
}

function getApproveID(element)
{
    var pressedBtn = element.id;
    //var lastChar = pressedBtn.charAt(pressedBtn.length-1);
    var lastChar = pressedBtn;
    document.getElementById("approveID").value = element.id;
   // alert("here is " + document.getElementById("approveID").value);
}

function getResolveID(element)
{
    var pressedBtn = element.id;
    var lastChar = pressedBtn;
    document.getElementById("resolveID").value = element.id;
    alert("here is " + document.getElementById("resolveID").value);
}

function getRequestID(element)
{

	var pressedBtn = element.id;
    
    document.getElementById("requestID").value = element.id

    var currentUrl = window.location.href;
    var urlLength  = currentUrl.length;
    //get the last 3 letters of the url
    var a = currentUrl.charAt(urlLength - 3);
    var b = currentUrl.charAt(urlLength - 2);
    var c = currentUrl.charAt(urlLength - 1);    
    var last = "" + a + b + c + "";
    //var d = currentUrl.(urlLength-21);
    
    var splitUrl = ""; // will contain an array

    // compare the last three letters of the url
    if(last != 'jsp') {
        splitUrl = currentUrl.split('?'); //will split the text when ? is found
        currentUrl = splitUrl[0]; // get the first element of splitUrl
    }

    var url=currentUrl,
	    seperator = (url.indexOf("?")===-1)?"?":"&",
	    newParam=seperator + "requestID=" +element.id;
	    newUrl=url.replace(newParam,"");
	    newUrl+=newParam;
	    window.location.href =newUrl;
}

function loadModalOnLoad()
{
    var currentUrl = window.location.href;
    var urlLength  = currentUrl.length;
    var a = currentUrl.charAt(urlLength - 3);
    var b = currentUrl.charAt(urlLength - 2);
    var c = currentUrl.charAt(urlLength - 1);
    var last = "" + a + b + c + "";
    
    
    if(  last != 'jsp') {
    	 $('#viewRequest-modal').foundation('reveal', 'open');
    }
}

function getRejectID(element)
{
    var pressedBtn = element.id;
    //var lastChar = pressedBtn.charAt(pressedBtn.length-1);
    var lastChar = pressedBtn;
    document.getElementById("rejectID").value = lastChar;
   // alert("here is " + document.getElementById("rejectID").value);
}


$(document).ready(function() {               
    $('#pUName').keyup(function(event) {  
        var username=$('#pUName').val();
        $.ajax({
            url: 'SignUpServlet',
            data: {
            	   "username": username,
//                   "status": "toread",
//                   "author": author,
//                   "title": title
                   },
            error: function (data) {
            	$('#pUNameError').text(data);
            	$('#pUNameError').css('color', 'red');
            },
            success: function (data) {
               
            	//alert("Ok: "+ data);
            	$('#pUNameError').text(data);
            	$('#pUNameError').css('color', 'green');
            },
            type: 'POST'
        });         
        
    });
});






//
//$.ajax({
//    url: 'AddToReadingList',
//    data: {"user": "<%=id%>",
//           "status": "toread",
//           "author": author,
//           "title": title},
//    error: function () {
//        alert("ERROR");
//    },
//    success: function (data) {
//        $("#buttonAddToReadBook").hide();
//        $("#buttonRemFromReadBook").show();
//        $("#buttonAddToFinishedBook").show();
//        $("#buttonRemFromFinishedBook").hide();
//    },
//    type: 'POST'
//});
//<%}else{%>
//alert("Login first");
//<%}%>

