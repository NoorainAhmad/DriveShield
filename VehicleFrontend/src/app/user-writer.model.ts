export class UserWriter {
  name: string;
  date_of_birth: Date;
  gender: string;
  address: string;
  dateOfJoin: Date;
  underwriterId: string;
  password: string;
  isAdmin: boolean;
  isdeleted: boolean;
  role: string; 

  constructor(
    name: string = '',
    date_of_birth: Date = new Date(),
    gender: string = '',
    address: string = '',
    dateOfJoin: Date = new Date(),
    underwriterId: string = '',
    password: string = '',
    isAdmin: boolean = false,
    isdeleted: boolean = false,
    role: string = ''
  ) {
    this.name = name;
    this.date_of_birth = date_of_birth;
    this.gender = gender;
    this.address = address;
    this.dateOfJoin = dateOfJoin;
    this.underwriterId = underwriterId;
    this.password = password;
    this.isAdmin = isAdmin;
    this.isdeleted = isdeleted;
    this.role = role;
  }
}
