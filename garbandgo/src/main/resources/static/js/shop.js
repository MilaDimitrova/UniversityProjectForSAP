document.addEventListener("DOMContentLoaded", function () {
    const orderButtons = document.querySelectorAll(".order-button");

    orderButtons.forEach(button => {
        button.addEventListener("click", function (e) {
            e.preventDefault();
            const productId = button.getAttribute("data-product-id");
            const form = document.getElementById("orderForm_" + productId);
            if (form) {
                form.submit();
            }
        });
    });
});
