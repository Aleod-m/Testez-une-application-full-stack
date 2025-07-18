import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { UserService } from './user.service';
import {User} from "../interfaces/user.interface";

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  const mockUser: User = {
    id: 1,
    email: 'email@email.fr',
    lastName: 'lastName',
    firstName: 'firstName',
    admin: true,
    password: 'secretpassword',
    createdAt: new Date(),
    updatedAt: new Date()
  }
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });
  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve a user', () => {
    const userId = '1';

    service.getById(userId).subscribe(user => {
      expect(user).toEqual(mockUser);
    })

    const request = httpMock.expectOne(`api/user/${userId}`);
    expect(request.request.method).toBe('GET');
    request.flush(mockUser);
  });

  it('should delete a user', () => {
    const id = '1';
    service.delete(id).subscribe(resp => {
      expect(resp).toBeTruthy()
    });
    const req = httpMock.expectOne(`api/user/${id}`)
    expect(req.request.method).toBe('DELETE')
    req.flush({})
  });
});
