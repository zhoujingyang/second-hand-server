var userId;
var sUrl = '/secondhand/goods/add';
$(document).ready(function() {
	userId = $('#userId').val();

	if (userId == null || userId == '') {
		$('#submit').css('am-disabled');
	} else {
		$('#submit').bind('click', submit);
	}


});

function submit() {
	var sTitle = $('#title').val();
	var stelPhone = $('#telphone').val();
	var sAddress = $('#address').val();
	var sMoney = $('#money').val();
	var sGoodsCategoryId = $('#goods_category_id').val();
	var sGoods_describe = $('#goods_describe').val();

	if (checkVal(sTitle) &&
		checkVal(stelPhone) &&
		checkVal(sAddress) &&
		checkVal(sMoney) &&
		checkVal(sGoodsCategoryId) &&
		checkVal(sGoods_describe)) {

		$('#message').html('');
		$.ajax({
			url: sUrl,
			type: "POST",
			data: {
				'telphone': stelPhone,
				'money': sMoney,
				'tradeAddress': sAddress,
				'goodsDescribe': sGoods_describe,
				'userId': userId,
				'goodsCategoryId': sGoodsCategoryId
			},
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				alert('success');
			}
		});

	} else {
		$('#message').html('<div class="am-alert am-alert-danger" data-am-alert><button type="button" class="am-close">&times;</button>信息填写不全</div>');
	}

}

function checkVal(val) {
	return !(val == null || val == '');
}