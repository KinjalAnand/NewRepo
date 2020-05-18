$(document).ready(function () {
    $('#add-button').click(function() {
    let marksArray= [];
    for (let step = 0; step < $('.marks').length; step++) {
        marksArray.push($('.marks')[step].innerHTML);}
   $.ajax({
      url: '/bin/Add',
      data: {
            marks: marksArray
      },
      error: function() {
         $('#info').html('<p>An error has occurred</p>');
         $('button').hide();
      },
      success: function(data) {
         var $marks = $('<h4>').text(data);
         $('#info')
            .html('<h3>Total Marks</h3>').append($marks);
//         $('button').hide();
         $('#afterCall').html('');
      },
      type: 'GET'
   });
})});