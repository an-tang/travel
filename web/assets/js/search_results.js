/************************************************/
/* Get data for search result page from back-end APIs */
/************************************************/

document.addEventListener('DOMContentLoaded', function () { // manipulate DOM elements after page has loaded completely
    // get the search keyword from the URL of the page
    var keyword = getKeyword();
    // stop searching if keyword equals null
    if (keyword == null) return;
    // create and send an HTTP request
    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/rest/tour/search/' + encodeURI(keyword), true);
    request.send();
    request.onload = function () {
        var jsonObject = JSON.parse(this.responseText);
        var containerInnerHTML = "";
        jsonObject.forEach(tour => {
            const html = `
            <div class="item clearfix">
                <div class="item_image"><img src="${tour.image}" alt=""></div>
                <div class="item_content">
                    <div class="item_title">${tour.name}</div>
                    <div class="item_price">${tour.price.toLocaleString()} đ</div>
                    <div class="rating rating_5" data-rating="5">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                    </div>
                    <div class="item_text">${tour.detail}</div>
                    <div class="item_more_link"><a href="doc.html?id=${tour.id}">Xem thêm</a></div>
                </div>
            </div>
            `;
            containerInnerHTML += html;
        });
        document.getElementsByClassName('items')[0].innerHTML = containerInnerHTML;
    };
    // set the title of the search results section on the page
    document.getElementById('section_title-with-q').innerText = `Kết quả tìm kiếm cho '${keyword}'`;

    function getKeyword() {
        var queryString = window.location.search;
        if (queryString !== "") {
            var searchParams = new URLSearchParams(queryString);
            return searchParams.get('q');
        }
        return null;
    }
});