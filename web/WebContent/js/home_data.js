/************************************************/
/* Get data for homepage from back-end APIs */
/************************************************/

document.addEventListener('DOMContentLoaded', function () { // manipulate DOM elements only after the page has loaded completely
    // declare HTTP requests and get the necessary DOM elements
    var reqToursByRegion, reqPopularTours;
    var topItems = document.getElementsByClassName('top_item');
    var northSlots = [topItems[0], topItems[1], topItems[2]];
    var centralSlots = [topItems[3], topItems[4], topItems[5]];
    var southSlots = [topItems[6], topItems[7], topItems[8]];

    // Get and display tours by region
    reqToursByRegion = new XMLHttpRequest();
    reqToursByRegion.open('GET', 'http://localhost:8080/rest/tour/area', true);
    reqToursByRegion.send();
    reqToursByRegion.onload = function () {
        const jsonObject = JSON.parse(this.responseText);        
        // Display tours from the North, Central and South
        displayRegionalTours(jsonObject.north, northSlots);
        displayRegionalTours(jsonObject.central, centralSlots);
        displayRegionalTours(jsonObject.south, southSlots);
    };

    // Get and display popular tours
    reqPopularTours = new XMLHttpRequest();
    reqPopularTours.open('GET', 'http://localhost:8080/rest/tour/topOrder', true);
    reqPopularTours.send();
    reqPopularTours.onload = function () {
        const jsonObject = JSON.parse(this.responseText);
        for (let i = 0; i < jsonObject.length; i++) {
            const tour = jsonObject[i];
            const html = `
            <a href="doc.html?id=${tour.id}">
                <div class="popular_item_image"><img src="${tour.image}" alt=""></div>
                <div class="popular_item_content">
                    <div class="popular_item_title">${tour.name}</div>
                    <div class="popular_item_price">${tour.price.toLocaleString()} đ</div>
                </div>
            </a>
            `;
            // update the DOM elements with the prepared HTLM text
            document.getElementsByClassName('popular_item')[i].innerHTML = html;
        }
    };

    // Display a list of tours from a region with a list of DOM elements containing them given
    function displayRegionalTours(regionalTours, DOMElements) {
        for (let i = 0; i < regionalTours.length; i++) {
            const tour = regionalTours[i];
            const html = `
            <a href="doc.html?id=${tour.id}">
                <div class="top_item_image"><img src="${tour.image}" alt=""></div>
                <div class="top_item_content">
                    <div class="top_item_title">${tour.name}</div>
                    <div class="top_item_price">${tour.price.toLocaleString()} đ</div>
                </div>
            </a>
            `;
            // update the DOM elements with the prepared HTLM text
            DOMElements[i].innerHTML = html;
        }
    }
});