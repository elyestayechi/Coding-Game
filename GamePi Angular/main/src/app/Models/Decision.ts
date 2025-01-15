import { User } from './User'; 
import { Scenario } from './Scenario'; 

export class Decision {
    id!: number; 
    action!: Decision.ActionType; 
    date!: Date; 
    profitOrLoss?: number; 
  
    
    user?: User; 
    scenario?: Scenario; 
  
    constructor(init?: Partial<Decision>) {
      Object.assign(this, init);
    }
  }
  
  
  export namespace Decision {
    export enum ActionType {
      BUY = 'BUY',
      SELL = 'SELL',
      HOLD = 'HOLD',
    }
  }
  