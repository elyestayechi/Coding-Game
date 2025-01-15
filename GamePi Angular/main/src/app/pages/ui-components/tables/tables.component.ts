import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MaterialModule } from 'src/app/material.module';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { GameService } from 'src/app/services/game.service';
import { ChangeDetectorRef } from '@angular/core';
import { ChartComponent, NgApexchartsModule } from 'ng-apexcharts';

export interface stockChart {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  yaxis: ApexYAxis;
  dataLabels: ApexDataLabels;
  grid: ApexGrid;
  tooltip: ApexTooltip;
  stroke: ApexStroke;
  fill: ApexFill;
}

@Component({
  selector: 'app-tables',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    MaterialModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    NgApexchartsModule,
  ],
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css'],
})
export class AppTablesComponent {
  userId = 1; // Example user ID
  username: string = '';
  balance: number = 0;
  startingDate: string = '';
  newsDate: string = '';
  headline: string = '';
  message: string = '';
  content: string = '';
  secondBackgroundVisible: boolean = false;
  newsScreenVisible: boolean = false;
  balanceFlickerB: boolean = false;
  isLoading: boolean = false; // Prevent overlapping calls
  newsQueue: any[] = []; // Queue for storing upcoming news
  restartScreenVisible: boolean = false; // To control the restart screen visibility
  decisionCount: number = 0; // Track the number of decisions made
  scenarioId: string = '';


  scenarioParagraphs: { [key: string]: string } = {
    scenario11: 'If you didn’t know, the best way to navigate this scenario is by using Value Investing. This strategy focuses on spotting undervalued companies with solid fundamentals. You’re looking for hidden gems with low P/E ratios or high book value, aiming to buy them at a discount and hold on until the market sees their true worth. Patience and a long-term perspective are key here.    ',
    scenario4: 'In this scenario, Growth Investing is your best ally. This strategy targets companies expected to grow faster than average. It’s all about identifying businesses with high potential and positioning yourself early to ride the wave of their success.',
    scenario5: 'The ideal strategy here is Momentum Investing. This approach is all about capitalizing on market trends—buying assets that are on the rise and selling those on the decline. Keep an eye on technical indicators like moving averages and RSI, but stay cautious of reversals that could catch you off guard.',
    scenario6: 'For this scenario, the ‘Buy the Dip’ strategy is the way to go. The idea is simple: purchase assets during temporary downturns with confidence that they’ll bounce back stronger. Focus on assets with solid fundamentals and use historical price data to spot the right opportunities.',
    scenario7: 'Short Selling is the strategy to consider for this situation. This involves selling borrowed shares at a high price and buying them back at a lower one to profit from a decline. Watch for weak earnings reports, overvalued stocks, or poor management signals, but remember—this is a high-risk move!',
    scenario8: 'The best way to handle this scenario is Cryptocurrency Investing. Dive into the world of digital assets like Bitcoin and Ethereum, but tread carefully—it’s a volatile market. Understand the technology, watch for regulatory changes, and stay alert for scams and manipulation.',
  };
  displayedParagraph: string = '';





  @ViewChild('chart') chart: ChartComponent = Object.create(null);
  public stockChart!: Partial<stockChart> | any;

  stockData = {
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
    "Pets.com": [
      { date: '2000-03-15', open: 10, high: 15, low: 7, close: 8 }, // Decision 1
      { date: '2000-06-15', open: 8, high: 12, low: 6, close: 7 }, // Decision 2
      { date: '2000-09-15', open: 7, high: 10, low: 5, close: 6 }, // Decision 3
    ],
    "S&P 500": [
      { date: '2008-01-01', open: 1400, high: 1500, low: 1300, close: 1350 }, // Decision 1
      { date: '2008-07-01', open: 1350, high: 1450, low: 1250, close: 1300 }, // Decision 2
      { date: '2008-12-01', open: 1300, high: 1400, low: 1200, close: 1250 }, // Decision 3
    ],
    GME: [
      { date: '2021-01-15', open: 40, high: 50, low: 30, close: 45 }, // Decision 1
      { date: '2021-02-15', open: 45, high: 55, low: 35, close: 50 }, // Decision 2
      { date: '2021-03-15', open: 50, high: 60, low: 40, close: 55 }, // Decision 3
    ],
    BTC: [
      { date: '2019-01-01', open: 8000, high: 9000, low: 7000, close: 8500 }, // Decision 1
      { date: '2019-06-01', open: 8500, high: 9500, low: 7500, close: 8800 }, // Decision 2
      { date: '2019-12-01', open: 8800, high: 10000, low: 8000, close: 9400 }, // Decision 3
    ],
  };
  constructor(private gameService: GameService, private cdr: ChangeDetectorRef) {
    this.updateChart('TSLA');
  }

  updateChart(stock: keyof typeof this.stockData): void {
    const seriesData = this.stockData[stock].map((entry) => ({
      x: entry.date,
      y: [entry.open, entry.high, entry.low, entry.close],
    }));

    this.stockChart = {
      series: [
        {
          name: stock,
          data: seriesData,
        },
      ],
      chart: {
        type: 'candlestick',
        height: 350,
      },
      xaxis: {
        type: 'datetime',
      },
      yaxis: {
        tooltip: {
          enabled: true,
        },
      },
      tooltip: {
        theme: 'dark',
      },
      grid: {
        show: true,
        strokeDashArray: 3,
      },
      stroke: {
        show: true,
        width: 1,
      },
      fill: {
        opacity: 1,
      },
    };
  }

  // Start the game
  startGame(): void {
    if (this.isLoading) return; // Prevent multiple clicks
    this.isLoading = true;

    this.gameService.startGame(this.userId).subscribe((response) => {
      console.log('Game started:', response);
      this.username = response.username;
      this.balance = response.balance;
      this.startingDate = response.startingDate;
      this.secondBackgroundVisible = true;
      this.newsScreenVisible = false;

      this.isLoading = false; // Allow further actions
    });
  }

  // Proceed to the first or next news
  proceedWithGame(): void {
    if (this.isLoading) return; // Prevent multiple clicks
    this.isLoading = true;

    this.gameService.proceedWithGame(this.userId).subscribe((response) => {
      console.log('Proceeding to next news:', response);

      this.newsQueue.push(response);

      // Display the first item in the queue
      this.displayNextNews();

      this.isLoading = false; // Allow further actions
      this.cdr.detectChanges(); // Ensure UI updates
    });
  }

  // Display the next news item from the queue
  displayNextNews(): void {
    if (this.newsQueue.length > 0) {
      const news = this.newsQueue.shift(); // Get the next news item
      this.newsDate = news.newsDate;
      this.headline = news.headline;
      this.content = news.content;
      this.message = news.message;

      this.newsScreenVisible = true;
      this.secondBackgroundVisible = false;
    }
  }

  playSound(): void {
    const soundPath = '/assets/images/backgrounds/ching.mp3';
    const audio = new Audio(soundPath);
    audio.play();
  }

  // Make a decision (Buy, Sell, Hold)
  makeDecision(action: string): void {
    if (this.isLoading) return; // Prevent overlapping calls
    this.isLoading = true;

    console.log('Making decision:', action);
    this.playSound();

    this.gameService.makeDecision(this.userId, action).subscribe((response) => {
      console.log('Decision made response:', response);

      this.triggerFlickerB(response.balance);

      this.newsQueue.push(response);

      this.decisionCount++;

      if (this.decisionCount >= 4) {
        

        this.newsScreenVisible = false;
        this.secondBackgroundVisible = false;
        this.restartScreenVisible = true;
      } else {
        this.displayNextNews();
      }

      this.isLoading = false; // Allow further actions
    });
  }

  // Restart the game
  restartGame(): void {
    if (this.isLoading) return; // Prevent multiple clicks
    this.isLoading = true;

    this.gameService.startGame(this.userId).subscribe((response) => {
      console.log('Game started:', response);
      this.username = response.username;
      this.balance = response.balance;
      this.startingDate = response.startingDate;
      this.secondBackgroundVisible = true;
      this.newsScreenVisible = false;

      this.isLoading = false; // Allow further actions
    });
  }

  // Trigger flicker effect for balance
  triggerFlickerB(newBalance: number): void {
    if (this.balance !== newBalance) {
      this.balanceFlickerB = true;

      setTimeout(() => {
        this.balanceFlickerB = false;
        this.balance = newBalance;
        this.cdr.detectChanges(); // Ensure UI updates
      }, 400);
    } else {
      this.balance = newBalance;
    }
  }
}


