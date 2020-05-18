$(document).ready(function () {
    $('.compare-button').click(function(e) {
    var path= $(e.target).data("path");
    var studentName =[];
    var studentMarks= []
    for (let step = 0; step < $('.SMarks').length; step++) {
        studentName.push($('.SMarks')[step].innerHTML);
        studentMarks.push($('.SMarks')[step].attributes.value.value);}
   $.ajax({
      url: path+'.testq4.html',
//      data: {
//            studentName: studentName,
//            studentMarks: studentMarks
//      },
      error: function() {
         $('.info').html('<p>An error has occurred</p>');
      },
      success: function(data) {
//          $('pre[data-path="'+path+'"]');
         var $students = $('<h3>').text(data);
//         $('.infoCompare')
         $('pre[data-path="'+path+'"]').html('<h2>Top 3 Students</h2>').append($students);
         $('button[data-path="'+path+'"]').hide();
//         $('#afterCompare').html('');
      },
      type: 'GET'
   });
})});