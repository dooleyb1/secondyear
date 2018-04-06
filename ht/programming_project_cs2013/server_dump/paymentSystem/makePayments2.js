//@author Seán Fitpatrick   (fitzpas5@tcd.ie)

//***This is a temp solution and needs to be replaced in the future

//***FUNCTIONS***//
/*!
 *@author Seán Fitpatrick   (fitzpas5@tcd.ie)
 * @brief Reads transactions from a file taken as input.
 * Assumes that you haven't require('fs'); yet.
 * Assumes that each transaction is on a new line and the address
 * and amount are seperqated by a space.
 * (e.g.
 *        47Vmj6BXSRPax69cVdqVP5APVLkcxxjjXdcP9fJWZdNc5mEpn3fXQY1CFmJDvyUXzj2Fy9XafvUgMbW91ZoqwqmQ6RjbVtp 1000000
 *        46tWrPQ7fxPG68XrF5T8DECC7Gfy1yjcVibjsBKKshFgE7mqWAWdr4UF1ZWqifMT6y5FHxF8CwK7DBHMBxQ1ZdqRFPFoeU1 2000000
 *        45kuSXfmtSWbxWkgUaFnNVfgbXAVFzK6VTgZhiRcUyE1F3czWoAbF3V1TAiwK1qtGK4GP32V9MRxz78yubiHHtt79Fd4GN7 3000000
    )
 *Constructs transfer objects from the file and returns them as an array
 * @return array of transfer objects
 */
function readTransactions(fileName)
{
  var fs = require('fs');
  var input;
  try{
    input = fs.readFileSync(fileName,'utf8');
  }catch (err) {
  // error opening file
  console.log("ERROR opening file. No transactions will be made from file.");
  return null;
}
  var transactions= input.split("\n");
  var address;        //address to send to
  var amount;         //amount of hashes to convert
  var transfers = []; //array of transfer
  for(var i=0;i<transactions.length;i++){	//input data into transfer objects
    var temp = transactions[i].split(",");
    address = temp[0];
    amount = temp[1];
    if(isBelowThreshold(hashesToValue(amount))){
      continue;
    }
    if(verifyTransaction(address,amount)==true){
      var x = {};   //current transfer object
      x.address = address;
      x.amount = hashesToValue(amount);
      transfers.push(x);
    }
  }
  return transfers;
}

//Helper funtion that makes simple error checks on address and amounts
function verifyTransaction(address,amount)
{
  //Test correct address lenght (95 char) and amount is not negative or zero
  if(address.length==95&&amount>0){
    return true;
  }
  return false;
}
//Rough conversion from hashes to monero
//TODO replace future
function hashesToValue(hashes) {
  return (hashes/19784875)*0.004046330787;
}
//Checks to see if amount is below a threshold
//threshold = 0.02
function isBelowThreshold(valueOfHashes){
  var threshold = 0.02
  if(valueOfHashes<threshold){
    return true;
  }
  return false;
}
//***END OF FUNCTIONS***//

var helpString = "Usage:\nnode makePayments [FilePath]\n(e.g. node makePayments ./transactionFiles/example.csv)\n";

//Input handle
if(process.argv.length<3){  //too few arguments
  console.log("Too few agruments\n"+helpString);
  return;
}
if(process.argv[2].toLowerCase()=="help"&&process.argv.length==3){ //help argument
  console.log(helpString);
  return;
}
else if (process.argv.length>3) {  //too many argumnets
  console.log("Too many arguments:\n"+helpString);
  return;
}

var moneroWallet = require('./monero-nodejs/lib/wallet.js');
var Wallet = new moneroWallet();
var fileName = process.argv[2];
var transfers = readTransactions(fileName);

if(transfers!=null){
  Wallet.transfer(transfers).then(function(result){
  console.log(result);
  if(result.indexOf("Error")>-1){   //wallet connection Error
    console.console.log("\nError connecting with wallet.\t No transfers made");
    return;
  }
  else if (result.indexOf("code: -4")>-1) {   //not enough money error code
    console.log("\ninsufficient funds to make transfers. No transfers will be made\n");
    return;
  }

  //outputs if transfers made succesfully
  console.log("transfers Made from "+fileName);
  console.log(transfers);
  return;
  });
}
else{
  console.log("No valid transfers in file");
}

