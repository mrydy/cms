
(function($){
	
	$.fn.cmsvalidate(opts){
		
		var _validate = $.validate;
		$.extend($.validate.prototype,opts||{});
		
	}
})(jQuery)