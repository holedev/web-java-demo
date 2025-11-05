<%-- 
    Document   : chart
    Created on : Oct 30, 2025, 3:44:55?PM
    Author     : phanl
--%>

<%@include file="header.jsp" %>

<div class="container">
    <h2>Stats</h2>
    <div>
    <canvas id="myChart"></canvas>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
  const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['Total Students', 'AVG'],
      datasets: [{
        label: 'Class Stats',
        data: [<%=request.getAttribute("total")%>,  <%=request.getAttribute("avg")%>],
        borderWidth: 1,
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
</script>

<%@include file="footer.jsp" %>
