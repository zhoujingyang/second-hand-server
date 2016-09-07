$(document).ready(function() {
  loadGoods(0);
  $.get('/guowang/user/getCurrentUsers', function(data) {
    $('#current').text(data);
  });

  $.get('/guowang/user/getUsers', function(data) {
    $('#total').text(data);
  });

  $.get('/guowang/version/version/all', function(data) {
    for (var i = 0; i < data.length; i++) {
      var v = data[i];
      if (v.type == 1) {
        $('#A').attr('placeholder', v.version);
      } else if (v.type == 2) {
        $('#I').attr('placeholder', v.version);
      }
    }
  })

  $('#verBtn').click(function() {
    var aV = $('#A').val();
    var iV = $('#I').val();

    if (aV) {
      $.ajax({
        url: '/guowang/version/version/1?version=' + aV,
        type: 'PUT',
        success: function() {
          $('#A').val("");
          $('#A').attr('placeholder', aV);
        }
      });
    }

    if (iV) {
      $.ajax({
        url: '/guowang/version/version/2?version=' + iV,
        type: 'PUT',
        success: function() {
          $('#I').val("");
          $('#I').attr('placeholder', iV);
        }
      });
    }
  })
});


function loadGoods(limit) {
  var end = parseInt(limit) + 20;
  var start = parseInt(limit);
  var url = "/guowang/feedback/list?start=" + start + "&end=" + end;


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

function pass() {

}