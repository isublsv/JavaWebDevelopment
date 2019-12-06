jQuery(function($){
    let isAjaxRequestSent = false;
    
    $('#login-form').submit(function (e) {
        let frm = $(this);

        if (!isAjaxRequestSent) {
            isAjaxRequestSent = true;
            $.ajax({
                url: frm.attr('action'),
                type: 'POST',
                dataType: 'text json',
                data: $('#login-form').serialize(),
                //TODO
                success: function (response) {
                    if (response.isSuccess) {
                        window.location.replace(decodeURI(response.isSuccess));
                    } else {
                        $('#warning-message').text(response.warningMessage);
                        isAjaxRequestSent = false;
                    }
                },
                error: function (request, status, error) {
                    $('#error-message').text(error);
                    isAjaxRequestSent = false;
                }
            });
        }
        e.preventDefault();
    });
});

