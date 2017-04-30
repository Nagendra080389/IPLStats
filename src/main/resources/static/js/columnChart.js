/**
 * Created by nagesingh on 4/29/2017.
 */

google.charts.load('current', {packages: ['corechart', 'bar']});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(drawChart);
google.setOnLoadCallback(changeMatchNumber);

var season = null;

function drawChart() {
    season = document.getElementById('ipl_Seasons');
    var jsonData = $.ajax({
        url: "http://localhost:8080/getMatchDetailsBySeason?season=" + season.value,
        dataType: "json",
        crossDomain: true,
        async: false
    }).responseText;

    var parsed = JSON.parse(jsonData);

    var arr = [];

    var validation = {

        isNumber: function (str) {
            var pattern = new RegExp('^[0-9]+$');
            return pattern.test(str);  // returns a boolean
        }
    };

    for (var x in parsed) {
        arr.push(parsed[x]);
    }

    var elementById = document.getElementById('matches_Number');

    if (elementById !== null && validation.isNumber(elementById.value)) {

        var matchByIDandSeason = elementById.value;
        var finalMatch = arr[parseInt(matchByIDandSeason) - 1];
        var mapOfOversAndRunsForFirstInnings = {};
        var mapOfOversAndRunsForSecondInnings = {};
        var matchDetails = finalMatch.matchDetails;
        var perOverRuns = 0;
        for (var i = 0; i < matchDetails.length; i++) {
            if (matchDetails[i].inning === 1) {
                if(matchDetails[i].over in mapOfOversAndRunsForFirstInnings){
                    mapOfOversAndRunsForFirstInnings[matchDetails[i].over] = parseInt(mapOfOversAndRunsForFirstInnings
                            [matchDetails[i].over])+matchDetails[i].total_runs;
                }else {
                    perOverRuns = 0;
                    perOverRuns =  matchDetails[i].total_runs + perOverRuns;
                    mapOfOversAndRunsForFirstInnings[matchDetails[i].over] = perOverRuns;
                }
            } else {
                if(matchDetails[i].over in mapOfOversAndRunsForSecondInnings){
                    mapOfOversAndRunsForSecondInnings[matchDetails[i].over] = parseInt(mapOfOversAndRunsForSecondInnings
                            [matchDetails[i].over])+matchDetails[i].total_runs;
                }else {
                    perOverRuns = 0;
                    perOverRuns =  matchDetails[i].total_runs + perOverRuns;
                    mapOfOversAndRunsForSecondInnings[matchDetails[i].over] = perOverRuns;
                }
            }
        }


        var dataArray = [['Overs', finalMatch.team1, finalMatch.team2]];
        var firstInningRun = 0;
        var secondInningRun = 0;
        var chartDataForTeam1 = [];
        var chartDataForTeam2 = [];
        for (var overs = 1; overs <= 20; overs++) {
            chartDataForTeam1.push(mapOfOversAndRunsForFirstInnings[overs]);
            chartDataForTeam2.push(mapOfOversAndRunsForSecondInnings[overs]);
            dataArray.push([overs, 0, 0]);
        }

        var materialOptions = {
            chart: {
                title: 'Cricket Performance'
            },
            width: 900,
            height: 500,
            hAxis: {
                gridlines: {count: 10},
                minValue : 1,
                maxValue : 20,
                title: 'Overs'
            },
            vAxis: {
                minValue : 1,
                title: 'Runs'
            },
            animation: { duration: 250 }
        };


        var data = new google.visualization.arrayToDataTable(dataArray);
        var chart = new google.visualization.ColumnChart(document.getElementById('columns_chart_div'));

        var team1Index = 0;
        var team2Index = 0;

        var drawChart = function() {


            if (team1Index < 20) {
                data.setValue(team1Index, 1, chartDataForTeam1[team1Index++]);
                chart.draw(data, materialOptions);
            }

            if(team2Index < 20){
                data.setValue(team2Index, 2, chartDataForTeam2[team2Index++]);
                chart.draw(data, materialOptions);
            }


        };

        google.visualization.events.addListener(chart, 'animationfinish', drawChart);
        chart.draw(data, materialOptions);
        drawChart();
    }
}

function changeMatchNumber() { // don't leak
    var elm = document.getElementById('matches_Number'), // get the select
        df = document.createDocumentFragment(),
        firstScriptVal = document.getElementById('ipl_Seasons').valueOf().value;
    elm.options.length = 0;
    var jsonData = $.ajax({
        url: "http://localhost:8080/getMatchesNumberBySeason",
        dataType: "json",
        crossDomain: true,
        async: false
    }).responseText;

    var parsed = JSON.parse(jsonData);

    var arr = [];

    arr.push(parsed[firstScriptVal]);

    for (var i = 0; i <= arr; i++) {
        var option = document.createElement('option');

        if (i === 0) {
            option.disabled = true;
            option.selected = true;
            option.appendChild(document.createTextNode(" -- select an option -- "));
        } else {
            option.value = i;
            option.appendChild(document.createTextNode("Match #" + i));
        }


        df.appendChild(option);
    }
    elm.appendChild(df); // append the document fragment to the DOM. this is the better way rather than setting innerHTML a bunch of times (or even once with a long string)
};
