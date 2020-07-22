package com.travel.servlet;

import com.travel.bean.ProvinceBean;
import com.travel.bean.TourBean;
import com.travel.enumerize.PagingSize;
import com.travel.enumerize.Region;
import com.travel.helper.URLHelpers;
import com.travel.service.ProvinceService;
import com.travel.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/province"})
public class ProvinceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String provinceIdParam = request.getParameter("prv_id");
            TourService tourService = new TourService();
            ProvinceService provinceService = new ProvinceService();

            if (provinceIdParam != null) {
                /* Province Tours Page */

                int provinceId = Integer.parseInt(provinceIdParam);
                String sortField = request.getParameter("sortField");
                String sortType = request.getParameter("sortType");
                int start = request.getParameter("start") != null
                        ? Integer.parseInt(request.getParameter("start"))
                        : 0;
                int size = request.getParameter("size") != null
                        ? Integer.parseInt(request.getParameter("size"))
                        : PagingSize.PROVINCE_TOURS.getValue();

                // Check if this is an AJAX request
                boolean isAjax = request.getHeader("x-requested-with") != null
                        && request.getHeader("x-requested-with").equals("XMLHttpRequest");

                // Get search result on current page
                ArrayList<TourBean> provinceTours = tourService.GetToursInProvinceByID(provinceId, sortField, sortType, start, size);
                request.setAttribute("tours", provinceTours);

                // Calculations for next page
                String showMoreURL = "";
                int nextStart = start + size;
                ArrayList<TourBean> nextProvinceTours = tourService.GetToursInProvinceByID(provinceId, sortField, sortType, nextStart, size);
                if (nextProvinceTours.size() > 0) {
                    showMoreURL = URLHelpers.buildRelativeURL(
                            "/province",
                            "prv_id", String.valueOf(provinceId),
                            "sortField", sortField,
                            "sortType", sortType,
                            "start", String.valueOf(nextStart),
                            "size", String.valueOf(size)
                    );
                }
                request.setAttribute("showMoreURL", showMoreURL);

                if (!isAjax) {
                    ProvinceBean province = provinceService.GetProvinceByID(provinceId);
                    String loginRedirectURL = URLHelpers.buildRelativeURL("/login", "redirect", "province", "prv_id", String.valueOf(provinceId));

                    request.setAttribute("provinceId", provinceId);
                    request.setAttribute("provinceName", province.getName());
                    if (loginRedirectURL != null) {
                        request.setAttribute("loginReplacementURL", loginRedirectURL);
                    }
                    request.getRequestDispatcher("provinceTours.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("components/search/tourListing.jsp").forward(request, response);
                }
            } else {
                /* Province Listing Page */

                // Get all possible provinces from all 3 regions
                ArrayList<ProvinceBean> northernProvinces = provinceService.GetProvincesByAreaID(Region.NORTH.getValue());
                ArrayList<ProvinceBean> centralProvinces = provinceService.GetProvincesByAreaID(Region.CENTRAL.getValue());
                ArrayList<ProvinceBean> southernProvinces = provinceService.GetProvincesByAreaID(Region.SOUTH.getValue());

                request.setAttribute("northernProvinces", northernProvinces);
                request.setAttribute("centralProvinces", centralProvinces);
                request.setAttribute("southernProvinces", southernProvinces);
                request.getRequestDispatcher("provinceListingPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
