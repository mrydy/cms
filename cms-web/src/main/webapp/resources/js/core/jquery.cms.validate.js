
(function($){
	var _validate = $.fn.validate;
	$.fn.cmsvalidate = function(opts){
		var _rules = $.extend({
			username:"required",
			password:"required",
			confirmPwd:{
				equalTo:"#password"
			},
			email:"email"
		},opts?(opts.rules||{}):{});
		var _messages = $.extend({
			username:"用户名不能为空",
			password:"密码不能为空",
			confirmPwd:"两次输入的密码不一致",
			email:"邮箱格式不正确"
		},opts?(opts.messages||{}):{});
		var _defaultOpts =$.extend(opts,{
				rules:_rules,
				messages:_messages,
				errorElement:opts?(opts.errorElement||"span"):"span",
				errorClass:opts?(opts.errorClass||"errorContainer"):"errorContainer"
		});
		$.extend($.fn.validate.prototype,_defaultOpts||{});
		_validate.call(this,_defaultOpts);
	}
})(jQuery)