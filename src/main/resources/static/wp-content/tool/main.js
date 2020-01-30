$(function() {
	
	$("#return_page_top").mouseup(function() {
		$('body,html').animate({ scrollTop : $('.site-header')[0].offset().top }, 500);
	});
	// 设置body的宽度。主要用于适应手机屏幕
	if (document.body.clientWidth < 1080 || document.body.offsetWidth < 1200 ||  document.body.scrollWidth < 1200 || window.screen.width < 1200 || window.screen.availWidth < 1200) {
		document.getElementsByTagName("body")[0].style.width = '1200px';
	}

});
/*

网页可见区域宽： document.body.clientWidth 
网页可见区域高： document.body.clientHeight 
网页可见区域宽： document.body.offsetWidth
网页可见区域高： document.body.offsetHeight
网页正文全文宽： document.body.scrollWidth 
网页正文全文高： document.body.scrollHeight 
屏幕分辨率的高： window.screen.height 
屏幕分辨率的宽： window.screen.width 
屏幕可用工作区高度： window.screen.availHeight 
屏幕可用工作区宽度： window.screen.availWidth

*/
