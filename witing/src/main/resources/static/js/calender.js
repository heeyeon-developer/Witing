
var currentTitle = document.getElementById('current-year-month');
var calendarBody = document.getElementById('calendar-body');
var today = new Date();
var first = new Date(today.getFullYear(), today.getMonth(),1);
var dayList = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
var monthList = ['January','February','March','April','May','June','July','August','September','October','November','December'];
var leapYear=[31,29,31,30,31,30,31,31,30,31,30,31];
var notLeapYear=[31,28,31,30,31,30,31,31,30,31,30,31];
var pageFirst = first;
var pageYear;
var tdGroup = [];

if(first.getFullYear() % 4 === 0){
    pageYear = leapYear;
}else{
    pageYear = notLeapYear;
}

function prev(){
    if(pageFirst.getMonth() === 1){
        pageFirst = new Date(first.getFullYear()-1, 12, 1);
        first = pageFirst;
        if(first.getFullYear() % 4 === 0){
            pageYear = leapYear;
        }else{
            pageYear = notLeapYear;
        }
    }else{
        pageFirst = new Date(first.getFullYear(), first.getMonth()-1, 1);
        first = pageFirst;
    }
    today = new Date(today.getFullYear(), today.getMonth()-1, 1);
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    removeCalendar();
    showCalendar();
    clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');
    clickStart();
    reshowingList();
}

function next(){
    if(pageFirst.getMonth() === 12){
        pageFirst = new Date(first.getFullYear()+1, 1, 1);
        first = pageFirst;
        if(first.getFullYear() % 4 === 0){
            pageYear = leapYear;
        }else{
            pageYear = notLeapYear;
        }
    }else{
        pageFirst = new Date(first.getFullYear(), first.getMonth()+1, 1);
        first = pageFirst;
    }
    today = new Date(today.getFullYear(), today.getMonth() + 1, 1);
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    removeCalendar();
    showCalendar(); 
    clickStart();
    clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');     
    reshowingList();
}

function clickStart(){
    for(let i = 1; i <= pageYear[first.getMonth()]; i++){
        tdGroup[i] = document.getElementById(i);
        tdGroup[i].addEventListener('click',changeToday);
    }
}

function showCalendar(){
    let monthCnt = 100;
    let cnt = 1;
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    for(var i = 0; i < 6; i++){
        var $tr = document.createElement('tr');
        $tr.setAttribute('id', monthCnt);   
        for(var j = 0; j < 7; j++){
            if((i === 0 && j < first.getDay()) || cnt > pageYear[first.getMonth()]){
                var $td = document.createElement('td');
                $tr.appendChild($td);     
            }else{
                var $td = document.createElement('td');
                $td.textContent = cnt;
                $td.setAttribute('id', cnt);                
                $tr.appendChild($td);
                cnt++;
            }
        }
        monthCnt++;
        calendarBody.appendChild($tr);
    }
}
showCalendar();

function removeCalendar(){
    let catchTr = 100;
    for(var i = 100; i< 106; i++){
        var $tr = document.getElementById(catchTr);
        $tr.remove();
        catchTr++;
    }
}

function showMain(){
    mainTodayDay.innerHTML = dayList[today.getDay()];
    mainTodayDate.innerHTML = today.getDate();
}

var clickedDate1 = document.getElementById(today.getDate());
clickedDate1.classList.add('active');
var prevBtn = document.getElementById('prev');
var nextBtn = document.getElementById('next');
prevBtn.addEventListener('click',prev);
nextBtn.addEventListener('click',next);

var sdate = new Date();

function paintDate(){
	for(let i = sdate.getDate(); i < today.getDate(); i++){
		tdGroup[i].classList.add('active');
    }
}


function changeToday(e){
    for(let i = 1; i <= pageYear[first.getMonth()]; i++){
        if(tdGroup[i].classList.contains('active')){
            tdGroup[i].classList.remove('active');
        }
    }
    
    clickedDate1 = e.currentTarget;
    clickedDate1.classList.add('active');
    today = new Date(today.getFullYear(), today.getMonth(), clickedDate1.id);
    if($('#edate').val() == '' && $('#sdate').val() == ''){
    	$('#sdate').val(today.getFullYear()+'-'+(today.getMonth()+1)+'-'+clickedDate1.id);
    }
    else if($('#edate').val() != '' && $('#sdate').val() != ''){
    	$('#edate').val('');
    	$('#sdate').val(today.getFullYear()+'-'+(today.getMonth()+1)+'-'+clickedDate1.id);
    }else{
    	sdate = new Date($('#sdate').val());
    	if(sdate.getFullYear() <= today.getFullYear() && sdate.getMonth() <= today.getMonth() && sdate.getDate() < today.getDate()){
    	  paintDate();
    	  $('#edate').val(today.getFullYear()+'-'+(today.getMonth()+1)+'-'+clickedDate1.id);
    	  var addprice = ((parseInt($('#cnt').val())-parseInt($("#cnt option:eq(0)").val())))*parseInt($('#addprice').text());
    	  var calcprice = (parseInt($('#basicprice').text())+parseInt(addprice))*parseInt(today.getDate()-sdate.getDate());
    	  $('#totalprice').val(calcprice);
    	}else{ 
    	  $('#sdate').val(today.getFullYear()+'-'+(today.getMonth()+1)+'-'+clickedDate1.id);
    	}
    }
    	
    showMain();
    keyValue = today.getFullYear() + '' + today.getMonth()+ '' + today.getDate();
    
    reshowingList();    
}

const changeCnt = (target) => {
	var bf = $('#basicprice').text();
	if(target.value > $("#cnt option:eq(0)").val()){
		
		var addprice = $('#addprice').text();
		var dif = parseInt(target.value) - parseInt($('#cnt option:eq(0)').val());
		var af = (parseInt(bf)+(parseInt(dif)*parseInt(addprice)))*parseInt(today.getDate()-sdate.getDate());
		$('#totalprice').val(af);
	}else{
		$('#totalprice').val(bf*parseInt(today.getDate()-sdate.getDate()));
	}
}

var stompClient = null;

function wsconnect() {
	var socket = new SockJS('http://127.0.0.1:8080/admin/wss');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/reserved', function(list) {
			alert(JSON.parse(list.body).length);
			
			for(var i=0; i<JSON.parse(list.body).length; i++){
				var start = JSON.parse(list.body)[i].sdate;
				var end = JSON.parse(list.body)[i].edate;
				for(let i = parseInt(start.substr(8,10))+1; i <= parseInt(end.substr(8,10))+1; i++){
					tdGroup[i].classList.add('reserved');
					tdGroup[i].removeEventListener('click',changeToday);
	    		}
			}
		});

	});
};

function connect() {
	var socket = new SockJS('http://127.0.0.1:8080/admin/ws');
	stompClient = Stomp.over(socket);//연결하기

	stompClient.connect({}, function(frame) { 
		console.log('Connected: ' + frame);
		var check = JSON.stringify({
		    'year' : today.getYear()-100+2000,
			'month' : today.getMonth()+1,
			'roomid' : $('#roomid').text()
		});//요청할 달, 방 데이터 생성
		
		stompClient.send("/reservedcheck", {}, check);//컨트롤러에 데이터 요청
		
		stompClient.subscribe('/reservedresult', function(list) { 
			for(var i=0; i<JSON.parse(list.body).length; i++){
				var start = JSON.parse(list.body)[i].sdate;
				var end = JSON.parse(list.body)[i].edate;
				for(let i = parseInt(start.substr(8,10))+1; i <= parseInt(end.substr(8,10))+1; i++){
					tdGroup[i].classList.add('reserved');
	    		}
			}
		});
	});
}

$(document).ready(function(){
	clickStart();
	connect();
	$('#prev').click(function(){
		connect();
	});
	$('#next').click(function(){
		connect();
	})
})