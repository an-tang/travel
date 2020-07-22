package com.travel.servlet;

import com.travel.bean.TourBean;
import com.travel.enumerize.PagingSize;
import com.travel.helper.URLHelpers;
import com.travel.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Check if this is an AJAX request
            boolean isAjax = request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equals("XMLHttpRequest");

            // Get request parameters
            String searchTerm = request.getParameter("q");
            String sortField = request.getParameter("sortField");
            String sortType = request.getParameter("sortType");
            int start = request.getParameter("start") != null
                    ? Integer.parseInt(request.getParameter("start"))
                    : 0;
            int size = request.getParameter("size") != null
                    ? Integer.parseInt(request.getParameter("size"))
                    : PagingSize.SEARCH.getValue();

            // Get search result on current page
            TourService tourService = new TourService();
            ArrayList<TourBean> searchResult = tourService.GetToursByName(searchTerm, sortField, sortType, start, size);
            request.setAttribute("tours", searchResult);

            // Calculations for next page
            String showMoreURL = "";
            int nextStart = start + size;
            ArrayList<TourBean> nextSearchResult = tourService.GetToursByName(searchTerm, sortField, sortType, nextStart, size);
            if (nextSearchResult.size() > 0) {
                showMoreURL = URLHelpers.buildRelativeURL(
                        "/search",
                        "q", searchTerm,
                        "sortField", sortField,
                        "sortType", sortType,
                        "start", String.valueOf(nextStart),
                        "size", String.valueOf(size)
                );
            }
            request.setAttribute("showMoreURL", showMoreURL);

            if (!isAjax) {
                String loginRedirectURL = URLHelpers.buildRelativeURL("/login", "redirect", "search", "q", searchTerm);
                request.setAttribute("q", searchTerm);
                if (loginRedirectURL != null) {
                    request.setAttribute("loginReplacementURL", loginRedirectURL);
                }
                request.getRequestDispatcher("search.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("components/search/tourListing.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
