<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:extends name="master">
  <layout:put block="content" type="REPLACE">
    <div class="container-fluid">
      <div class="row" id="order-index-header">
        <div class="col-md-1 col-xs-1">
          <span class="order-index-title">ORDERS</span>
        </div>
        <div class="col-md-10 col-xs-10 text-center">
          <img id="order-index-icon" src="/img/small-logo.png"/>
        </div>
      </div>
      <div class=row>
        <div class="col-xs-12 no-padding">
          <div class="list-group list-special">
            <core:forEach var="order" items="${orders}">
              <a href="/order?id=${order.id}" class="list-group-item list-special-item">
                <div class="row">
                  <div class="col-xs-8 text-uppercase">
                      ${order.customerName}
                  </div>
                  <div class="col-xs-3 index-status text-uppercase">
                      ${order.status}
                  </div>
                  <div class="col-xs-1">
                      <i class="fa fa-caret-right"></i>
                  </div>
                </div>
              </a>
            </core:forEach>
          </div>
        </div>
      </div>
    </div>
  </layout:put>
</layout:extends>
