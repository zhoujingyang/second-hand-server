$(document).ready(function() {
    loadGoods(0, 9);
});


function loadGoods(limit, goodsStatus) {
    var url = "/secondhand/goods/list/order?order=1&orderByString=create_time&limitCount=" + limit;

    if (parseInt(goodsStatus) <= 4)
        url += "&goodsStatus=" + goodsStatus;

    $.get(url, function(data) {

        var html;
        for (var i = 0; i < data.length; i++) {
            html += createContext(data[i]);
        }

        $('#list').html(html);

        $('#pre').attr('limit', limit == 0 ? 0 : parseInt(limit) - 20);
        $('#next').attr('limit', data.length != 20 ? limit : parseInt(limit) + 20);

        $('#pre').attr('status', goodsStatus);
        $('#next').attr('status', goodsStatus);

    });
};


var statusStr = {
    0: '待审核',
    1: '审核通过',
    2: '审核不通过',
    4: '交易完成'
}

function createContext(good) {

    var sTr = '<tr><td>' + good.id + '</td><td>' + good.title + '</td><td>' + good.goodsCategoryId + '</td><td>' + good.money + '</td><td>' + good.createTime + '</td><td>' + statusStr[good.goodsStatus] + '</td><td><a href="detail.html?id=' + good.id + '" target="_blank" style="cursor:pointer">详情</a></td></tr>'

    return sTr;

}

function page(dom) {
    var limit = $(dom).attr('limit');
    var status = $(dom).attr('status');
    loadGoods(limit, status);
}

function active(dom, status) {
    $("li[role='presentation']").removeAttr('class')
    $(dom).attr('class', 'active');
    loadGoods(0, status);
}