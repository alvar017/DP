<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<body>

</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

<canvas id="grafica"></canvas>

<script type="text/javascript">

var finalGraph = document.getElementById('grafica').getContext('2d');

var allshouts = ${statistics.get('count.all.shouts')};
//var allshouts = 6;
var shortshouts = ${statistics.get('count.short.shouts')};
//var shortshouts = 4;
var longshouts = ${statistics.get('count.long.shouts')};
//var longshouts = 2;

var chartBar = new Chart(finalGraph, {
    type: 'bar',
    data: {
      labels: ["All shout", "Short shout", "Long shout"],
      datasets: [
        {
          label: "Number",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f"],
          data: [allshouts, shortshouts, longshouts],
          backgourdColor:'blue'
        }
      ]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>