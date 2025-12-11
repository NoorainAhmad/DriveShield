export class Insurance {

    make: string;
    model: string;
    startDate: Date;
    endDate: Date;
    premiumAmount: number;
    insuranceId :string ;
   

  //   private String vehicleNo;
	// private String make;
	// private String model;
	// private Date startDate;
	// private Date endDate;
	// private double premiumAmount;
	// private String insuranceId;
   
    constructor(make: string, model: string, startDate: Date, endDate:Date, premiumAmount: number, insuranceId:string) {
   
      this.make = make;
      this.model = model;
   
      this.startDate = startDate;
      this.endDate=endDate;
   
      this.premiumAmount = premiumAmount;
      this.insuranceId=insuranceId;
    }
   
   }
   
   