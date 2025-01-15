import { Scenario } from './Scenario'; // Import Scenario model

export class News {
  id!: number; // News ID
  headline!: string; // Headline (required)
  content?: string; // Optional content (max length: 1000 in the backend)
  date?: Date; // Date of the news

  // Relationship
  scenario?: Scenario; // Many-to-one relationship with Scenario

  constructor(init?: Partial<News>) {
    Object.assign(this, init);
  }
}
