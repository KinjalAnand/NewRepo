$(document).ready(function () {
    $('#compare-button').click(function() {
    var studentName =[];
    var studentMarks= []
    for (let step = 0; step < $('.SMarks').length; step++) {
        studentName.push($('.SMarks')[step].innerHTML);
        studentMarks.push($('.SMarks')[step].attributes.value.value);}
   $.ajax({
      url: '/bin/Compare',
      data: {
            studentName: studentName,
            studentMarks: studentMarks
      },
      error: function() {
         $('#info').html('<p>An error has occurred</p>');
      },
      success: function(data) {
         var $students = $('<h3>').text(data);
         $('#infoCompare')
            .html('<h2>Top 3 Students</h2>').append($students);
         $('#afterCompare').html('');
      },
      type: 'GET'
   });
})});