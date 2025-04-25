document.addEventListener("DOMContentLoaded", function() {
    var orderLinks = document.querySelectorAll('.order-link');

    orderLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            var prodId = link.getAttribute('data-prod-id');
            var formId = 'orderForm_' + prodId;
            var form = document.getElementById(formId);
            if(form){
                form.submit();
            }
        });
    });
});
