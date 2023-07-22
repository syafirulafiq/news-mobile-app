
<!doctype html>
<html>
<head>
  <title>View Report</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }
    h1 {
      color: #333;
    }
    .report-card {
      margin-bottom: 20px;
      padding: 16px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .report-card small {
      color: #777;
    }
    .report-card blockquote {
      font-style: italic;
      margin: 8px 0;
    }
  </style>
</head>
<body>

<h1>View Reports</h1>

<?php foreach ($result as $row) { ?>
  <div class="report-card">
    <p><strong>Date:</strong> <?=$row['date']?></p>
    <p><strong>Email:</strong> <?=$row['email']?></p>
    <p><strong>ReportID:</strong> <?=$row['id']?></p>
    <p><strong>Name:</strong> <a href="mailto:<?=$row['email']?>"><?=$row['name']?></a></p>
    <p><strong>Report:</strong></p>
    <blockquote><em><?=$row['reports']?></em></blockquote>
  </div>
<?php } ?>

</body>
</html>