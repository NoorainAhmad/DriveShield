export class Insurance {
    vehicleNo: string;
    make: string;
    model: string;
    startDate: Date;
    endDate: Date;
    premiumAmount: number;
    insuranceId: string;
  
    constructor(
      vehicleNo: string = '',
      make: string = '',
      model: string = '',
      startDate: Date = new Date(),
      endDate: Date = new Date(),
      premiumAmount: number = 0,
      insuranceId: string = ''
    ) {
      this.vehicleNo = vehicleNo;
      this.make = make;
      this.model = model;
      this.startDate = startDate;
      this.endDate = endDate;
      this.premiumAmount = premiumAmount;
      this.insuranceId = insuranceId;
    }
  }
  