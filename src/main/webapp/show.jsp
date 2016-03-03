<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:extends name="master">
  <layout:put block="content" type="REPLACE">
    <div class="container-fluid">
      <div class="row" id="order-index-header">
        <div class="col-md-4 col-xs-4">
          <a href="/orders">
            <i class="fa fa-caret-left order-index-title"></i>
            <span class="order-index-title">BACK</span>
          </a>
        </div>
        <div class="col-md-4 col-xs-4 text-center">
          <img id="order-index-icon" src="/img/small-logo.png"/>
        </div>
      </div>
      <div class="row">
        <div id="house-image" class="col-md-7 col-xs-7 no-padding">
          <p id="customer-name-p">${order.customerName}</p>
        </div>
        <div id="location-image" class="col-md-5 col-xs-5 no-padding"></div>
      </div>
      <div id="show-info-row" class="row">
        <div class="col-xs-6 no-padding">
          Status: <span class="index-status text-uppercase">${order.status}</span>
        </div>
        <div class="col-xs-6 no-padding">
          Notification: <span class="index-status text-uppercase">${order.notificationStatus}</span>
        </div>
      </div>
      <div class="row show-button-row">
        <div class="col-xs-12 text-center">
          <form method="POST" action="/order/pickup?id=${order.id}" accept-charset="UTF-8">
            <button type="submit" class="btn show-button">
              <div class="col-xs-3 text-center">
                <img id="order-index-icon" src="/img/small-logo.png"/>
              </div>
              <div class="col-xs-6 text-center">
                PICK-UP
              </div>
            </button>
          </form>
        </div>
      </div>
      <div class="row show-button-row">
        <div class="col-xs-12 text-center">
          <form method="POST" action="/order/deliver?id=${order.id}" accept-charset="UTF-8">
            <button type="submit" class="btn show-button">
              <div class="col-xs-6 col-xs-offset-3 text-center">
                DELIVER
              </div>
              <div class="col-xs-3 text-center">
                <i class="fa fa-bicycle"></i>
              </div>
            </button>
          </form>
        </div>
      </div>
    </div>
  </layout:put>
</layout:extends>
