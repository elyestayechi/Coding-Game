import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js/auto';
import { CandlestickController, CandlestickElement } from 'chartjs-chart-financial';

Chart.register(CandlestickController, CandlestickElement);


export const MOCK_STOCK_DATA = {
  LEHLQ: [
    { date: '2007-03-15', open: 20, high: 25, low: 18, close: 22 },
    { date: '2007-07-31', open: 22, high: 28, low: 21, close: 27 },
    { date: '2008-09-15', open: 27, high: 30, low: 25, close: 26 },
    { date: '2008-11-12', open: 26, high: 29, low: 24, close: 28 },
  ],
  TSLA: [
    { date: '2020-01-29', open: 100, high: 120, low: 95, close: 115 },
    { date: '2020-05-01', open: 115, high: 125, low: 110, close: 123 },
    { date: '2020-06-11', open: 123, high: 135, low: 118, close: 130 },
    { date: '2020-07-06', open: 130, high: 150, low: 125, close: 145 },
  ],
};




@Component({
  selector: 'app-stock-chart',
  template: `<canvas id="stockChart"></canvas>`,
})
export class StockChartComponent implements OnInit {
  ngOnInit() {
    this.renderChart('LEHLQ'); // Default to LEHLQ
  }

  renderChart(stockSymbol: string) {
    const stockData = MOCK_STOCK_DATA[stockSymbol].map((entry) => ({
      x: entry.date,
      o: entry.open,
      h: entry.high,
      l: entry.low,
      c: entry.close,
    }));

    new Chart('stockChart', {
      type: 'candlestick',
      data: {
        datasets: [
          {
            label: `${stockSymbol} Stock Prices`,
            data: stockData,
            borderColor: 'rgba(0, 123, 255, 1)',
            backgroundColor: 'rgba(0, 123, 255, 0.5)',
          },
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: true,
          },
        },
        scales: {
          x: {
            type: 'time',
            time: {
              unit: 'day',
            },
          },
          y: {
            beginAtZero: false,
          },
        },
      },
    });
  }
}
