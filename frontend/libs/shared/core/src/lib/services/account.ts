export class Account {
  accountId: string;
  credentialsExpired: boolean;
  enabled: boolean;
  locked: boolean;
  firstName: string;
  lastName: string;
  userName: string;
  roles: Role[];
}

export class Role {
  id: number;
  name: string;
  privileges: Privilege[];
}

export class Privilege {
  id: number;
  name: string;
}
