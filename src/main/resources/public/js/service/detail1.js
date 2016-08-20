$(document).ready(function() {
    loadDetail();
});

var statusStr = {
    0: '待审核',
    1: '审核通过',
    2: '审核不通过',
    4: '交易完成'
}

function loadDetail() {
    var id = getUrlParam('id');
    var url = '/secondhand/goods/goods/' + id;
    $.get(url, function(goods) {
        var html = '<h3>' + goods.title + '</h3><p>&nbsp;' + goods.goodsDescribe + '</p><p>电话:&nbsp;&nbsp;' + goods.telphone + '</p>' + '<p>地址:&nbsp;&nbsp;' + goods.tradeAddress + '</p><p>发布时间:&nbsp;&nbsp;' + goods.createTime + '</p>';
        $('#info').html(html)
    });
}

function pass(status) {
    var url = '/secondhand/goods/update';
    var id = getUrlParam('id');
    $.post(url, {
        'id': id,
        'goodsStatus': status
    }, function(data) {
        loadDetail();
    })

}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}