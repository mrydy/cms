$(function(){
	$("#listTable").trColorChange();
	$("a.delete").confirmOperator();
	$("a.cleanUsers").confirmOperator({msg:"清空用户不可逆！确定清空操作吗？"});
})