var youlhAPI="http://www.youlunhui.com/youlh";
var pageinfo = {
	page : 1,
	t : 0,
	size : 15,
	tp : 0
};
function initpageInfo(total) {
	
	$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});
	$('#title-table-checkbox').unbind('click');
	$('#title-table-checkbox').click(function(){

		if($(this).attr('selectedFlag')==undefined||$(this).attr('selectedFlag')==0){
			$(this).attr('selectedFlag',1);
			$("#table_content .icheckbox_square-green").addClass("checked");
		}
		else{
			$(this).attr('selectedFlag',0);
			$("#table_content .icheckbox_square-green").removeClass("checked");
		}
	});
	
	if(total==undefined||total==null){
		$("#pager").parent().parent().hide();
		return;
	}
	$('#datacount').html(total);
	$('#txtNowPage').keyup(function(evt) {
		if (evt.keyCode == 13) {
			var page = $('#txtNowPage').val();
			if (page < 0)
				page = 1;

			loadData(page);
		}
	});
	if (total > pageinfo["size"]) {
		if (total % pageinfo["size"] == 0)
			pageinfo["tp"] = total / pageinfo["size"];
		else
			pageinfo["tp"] = Math.ceil(total / pageinfo["size"]);
		$("#pager").pager({
			pagenumber : pageinfo["page"],
			pagecount : pageinfo["tp"],
			buttonClickCallback : PageClick
		});
		$('#pagecount').html(pageinfo["tp"]);
		$('#txtNowPage').val(pageinfo["page"]);
	} else {
		if(total>0)
			$('#pagecount').html(1);
		else
			$('#pagecount').html(0);
		$('#pager').html('');
		$('#txtNowPage').hide();
	}

}

PageClick = function(pageclickednumber) {
	$("#pager").pager({
		pagenumber : pageclickednumber,
		pagecount : pageinfo["tp"],
		buttonClickCallback : PageClick
	});
	loadData(pageclickednumber);
}

function closeDialog(obj1, obj2) {
	$('#' + obj1).css({
		display : 'none'
	});
	$('#' + obj2).css({
		display : 'none'
	});
}

function openDialog(obj1, obj2) {
	$('#' + obj1).css({
		display : 'block'
	});
	$('#' + obj2).css({
		display : 'block'
	});
}
function isPhone(s) {
	var patrn = /^0?(13[0-9]|15[012356789]|17[012356789]|18[0123456789]|14[57])[0-9]{8}$/;
	if (!patrn.exec(s))
		return false;
	return true;
}
function isEmail(str){
	 var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	 return reg.test(str);
	}
//02134536156
function isPhoneFixed(s) {
	var patrn = /^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/;
	if (!patrn.exec(s))
		return false;
	return true;
}
function initInput(txtId,spanId,text,lengthTxt){
	var title=document.getElementById(txtId);
	if(title!=null && title!= undefined){
		title.onfocus=function(){
			if(this.value ==text){
				this.value='';
			}
			else{
				//this.className='input-color-focus';
			}
				
			};
			title.onblur=function(){
			if(this.value==''){
			  this.value=text;
			  //this.className='input-color';
			}
			else{
				//this.className='input-color-focus';
			}
				
			};
			title.onkeyup=function(){
				if(this.value !=''){
					//this.className='input-color-focus';
				}
				if(this.value.length <= lengthTxt)
					$('#'+spanId).html(this.value.length+'/'+lengthTxt);
				else{
					$('#'+spanId).html(lengthTxt+'/'+lengthTxt);
					this.value = this.value.substr(0,lengthTxt);
				}
					

			};
	}
	
}
