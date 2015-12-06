add.addEvent(window,"load",function() {
	var box = document.getElementById("box"),       //弹出框
		title = document.getElementById("title"),   //可拖动的区域
		mask = document.getElementById("mask"),     //覆盖层
		close = document.getElementById("close"),   //关闭按钮
		choose = document.getElementById("choose"), //选择考试科目按钮
		send = document.getElementById("send"),     //确定考试按钮

		/*鼠标相对于box左边框的距离*/
		disX = 0,
		disY = 0,
		
		
		/*判断是否可拖拽的标记*/
		pull = false;	


	add.addEvent(title,"mousedown",function(event) {
		event = event || window.event;
		add.preventDefault(event);

		disY = event.clientY - box.offsetTop;
		disX = event.clientX - box.offsetLeft;
		box.style.cursor = "move";

		/* 可拖动 */
		pull = true;
	});

	add.addEvent(document,"mouseup",function() {
		pull = false;
		box.style.cursor = "";
	});
 
	/* 移动鼠标 */
	add.addEvent(document,"mousemove",function(event) {
		event = event || window.event;
		add.preventDefault(event);

		/*如果可以拖动的话*/
		if(pull === true) {

			/*弹出框的横坐标等于鼠标的当前横坐标减去鼠标与box左边框的距离*/
			var boxX = event.clientX - disX;
			var boxY = event.clientY - disY;
			
			/*当弹出框移动到页面最右边的时候弹出框距离页面最左边的距离*/
			var maxX = viewWidth - box.offsetWidth;
			var maxY = viewHeight - box.offsetHeight;
			
			boxX = Math.min(maxX,Math.max(0,boxX));
			boxY = Math.min(maxY,Math.max(0,boxY));
			
			/*设置弹出框的定位*/
			box.style.left = boxX + "px";
			box.style.top = boxY + "px";			
		}
	});
	
	/*显示弹出窗口*/
	//add.addEvent(choose,"click",function() {	
	//	console.log("choose");	
	//	showBox();		
	//});	

	/*关闭弹出窗口*/
	add.addEvent(close,"click",function() {
		closeBox();
	});		
	
	/*当改变浏览器窗口大小的时候始终保持弹出框居中*/
	add.addEvent(window,"resize",function() {
		center(box);
	});	
});

/*居中*/
function center(element) {		
	var eleWidth = element.offsetWidth,//弹出框的宽度
	eleHeight = element.offsetHeight;

	//可视窗口的宽度
	viewWidth = document.documentElement.clientWidth || document.body.clientHeight;
	viewHeight = document.documentElement.clientHeight || document.body.clientHeight;
	
	element.style.left = (viewWidth - eleWidth) / 2 + "px";
	element.style.top = (viewHeight - eleHeight) / 2 + "px";
};

/*覆盖全屏*/
function cover (element) {
	element.style.width = viewWidth + "px";
	element.style.height = viewHeight + "px";
};

/*显示*/
function showBox(flag) {
	if(flag) {
		box.style.display = "block";
		mask.style.display = "block";
		center(box);
		cover(mask);
	} else {
		box.style.display = "none";
		mask.style.display = "none";
		alert("您没有考试资格！");
	}
	
}

/* 关闭 */
function closeBox() {
	box.style.display = "none";
	mask.style.display = "none";
}
