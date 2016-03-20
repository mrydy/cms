(function($){
	$.fn.myaccordion = function(opts){
		var settings = $.extend({
				selectedClz:"navSelected",
				titleTagName:"h3"
		},opts||{});
		$(this).find(settings.titleTagName).css("cursor","pointer");
		$(this).find("ul>"+settings.titleTagName)
			.nextAll()
			.css("display","none");
		$(this).find("ul."+settings.selectedClz+">"+settings.titleTagName)
		.nextAll()
		.css("display","block");
	}
})(jQuery)