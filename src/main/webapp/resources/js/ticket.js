$(function() {

    $('#leftColumn').css({height:$('#rightColumn').height()});

    M.updateTextFields();
    var datepickerinctsnce = $('.datepicker').datepicker({ format: 'dd.mm.yyyy' }, { showClearBtn: true });

    $("#button").click( function() {
        $( ".datepicker-done" ).click();
        alert("f");
    });

        $(document).on('click', '#Pay', function() {

            var datareq = $("#ticket :input").serialize();
            $.ajax({
                type: "POST",
                url: "http://localhost:8081/buyTicket",
                data: datareq,
                success: function (data) {
                    document.body.innerHTML = data;
                    $(document).ready(function(){
                        $('#birthdate').datepicker();
                        M.updateTextFields();
                        var datepickerinctsnce = $('.datepicker').datepicker({ format: 'dd.mm.yyyy' }, { showClearBtn: true });
                    });
                }
            });

    });

});