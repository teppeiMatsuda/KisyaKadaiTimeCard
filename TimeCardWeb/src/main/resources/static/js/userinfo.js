$(function(){
var  userid;
$(".userinfoid").bind('click', function(){
var  id = $(this).attr('id');

if(userid==null){
	userid = id;
}
$('#edit').val(userid);
$('#delete').val(userid);
});
}