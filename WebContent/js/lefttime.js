add.addEvent(window,"load",function() {
	var oStart = document.getElementById("start"),
		startTime = new Date();
	settime = 90*60;	
	showTime(settime,startTime);
});

function showTime(setTime,startTime) {		
	var nowtime = new Date(),//当前时间			
		h = parseInt(settime/(60*60)%24),
		m = parseInt(settime/60%60),
		s = parseInt(settime%60),
		timer = null;		
	document.getElementById("settime").innerHTML = h + "小时" + m + "分钟" + s + "秒";
	settime = settime-1;
	if(settime <= 0) {
		clearTimeout(timer);
		document.getElementById("leftTime").innerHTML = "考试结束";
	} else {
		timer = setTimeout(function() {
			showTime(settime,startTime);
		},1000);
	}
	
}