import { TestBed } from '@angular/core/testing';

import { SongCardService } from './song-card.service';

describe('SongCardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SongCardService = TestBed.get(SongCardService);
    expect(service).toBeTruthy();
  });
});
