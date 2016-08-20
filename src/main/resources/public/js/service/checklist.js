$(document).ready(function() {
    loadGoods(0);
    $.get('/guowang/user/getCurrentUsers',function(data){
       $('#current').text(data);
    });

     $.get('/guowang/user/getUsers',function(data){
           $('#total').text(data);
        });
});


function loadGoods(limit) {
    var end = parseInt(limit) + 20;
     var start = parseInt(limit);
    var url = "/guowang/feedback/list?start="+start+"&end="+end;


    $.get(url, function(data) {

        var html;
        var feedbacks = data['data'];
        var totalCount = data['count'];
        for (var i = 0; i < feedbacks.length; i++) {
            html += createContext(feedbacks[i]);
        }

        $('#list').html(html);
        $('#pre').attr('limit', limit == 0 ? 0 : parseInt(limit) - 20);
        $('#next').attr('limit', feedbacks.length != 20 ? limit : parseInt(limit) + 20);

    });
};



function createContext(good) {
    var sTr = '<tr><td>' + good.username + '</td><td>' + good.content + '</td><td>' + good.createTime + '</td>';
    return sTr;

}

function page(dom) {
    var limit = $(dom).attr('limit');
    loadGoods(limit, status);
}

