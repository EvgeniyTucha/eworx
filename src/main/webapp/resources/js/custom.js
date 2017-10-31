$(document).ready(function() {
    var pathname = window.location.pathname;
    $('.navigation > li > a[href="'+pathname+'"]').parent().addClass('active');


    $('#cart-discount').popover({
        'placement':'right',
        'content':'Congratulation, You get 10% discount!'
    }).popover('show');
})

