

$(document).foundation();


$(document).ready(function(){
	var bodyheight = $(window).height();
	$(".windowheight").css('min-height', bodyheight);
	$(window).resize(function(){
		var bodyheight = $(window).height();
		$(".windowheight").css('min-height', bodyheight);
	});
});

$('#datetimepicker').datetimepicker({
    datepicker:false,
    format:'H:i',
    
});
$('#datetimepicker2').datetimepicker({
    datepicker:false,
    format:'H:i',
});

function addElement()
{
    alert("HELLO");
    var div = document.createElement("div");
    div.className = "row request-box";
    div.id = "request-2";
    div.innerHTML = "<div class = \"large-2 columns\"> 10:00 AM </div>"+
                    "<div class = \"large-2 columns\"> March 5, 2015</div>"+
                    "<div class = \"large-5 columns\">"+
                        "<p>Appointment with Siao Long Bao <br>"+
                        "Area of Concern: So delicious :> Big tummy :><br>"+
                        "Time: 12:00 PM</p>"+
                    "</div>"+
                    "<div class = \"large-3 columns\"> <input type=\"button\" class = \"appointment-request-button\"                                                        value=\"Remove\" onClick=\"removeElement('mid-content','request-1');\"> "+
                    "<input type=\"button\" class = \"appointment-request-button\" value=\"Approve\" onClick=\"addElement();\"></div>";
//   document.body.appendChild(div);   
//    document.body.prepend(div);
    $(document).ready(function()
    {

       $("#mid-content").prepend(div);

    });

}

$(function() {
	$('a[href*=#]:not([href=#])').click(function() {
		if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
			var target = $(this.hash);
			target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
			if (target.length) {
				$('html,body').animate({
					scrollTop: target.offset().top
				}, 1000);
				return false;
			}
		}
	});
});


function validateLogin()
{
	if (document.getElementById("logusername").value.length < 1 || document.getElementById("logpassword").value.length < 1)
	{
	    document.getElementById("logusername").style.borderColor = "Red";
	    document.getElementById("logpassword").style.borderColor = "Red";
	$('#blankFields-modal').foundation('reveal', 'open');
	    return false;
	}
	return true;
}


function getHospID(element)
{
	var pressedBtn = element.id;
		var lastChar = pressedBtn.charAt(pressedBtn.length-1);
		document.getElementById("hospID").value = lastChar;
		alert("here is " + document.getElementById("hospID").value);
}

function getDocID(element)
{
	var pressedBtn = element.id;
		var lastChar = pressedBtn.charAt(pressedBtn.length-1);
		document.getElementById("docID").value = lastChar;
		alert("here is " + document.getElementById("docID").value);
}

function clicked(i)
{
	switch(i)
	{
		case 1: alert("You clicked Contact");break;
		case 2: alert("You clicked Guidelines");break;
		case 3: alert("You clicked Hospitals");break;
		case 4: alert("You clicked About");break;
		case 5: alert("List of specializations here.");
	}
}


function removeElement(parentDiv, childDiv){
	if (childDiv == parentDiv) {
		alert("The parent div cannot be removed.");
	}
	else if (document.getElementById(childDiv)) {     
		var child = document.getElementById(childDiv);
		var parent = document.getElementById(parentDiv);
		parent.removeChild(child);
	}
	else {
		alert("Child div has already been removed or does not exist.");
		return false;
	}
}
