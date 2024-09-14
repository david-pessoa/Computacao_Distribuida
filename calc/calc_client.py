import grpc
import calc_pb2
import calc_pb2_grpc

def run():
	with grpc.insecure_channel('localhost:50054') as channel:
		stub = calc_pb2_grpc.CalculatorStub(channel)

		request = calc_pb2.MultRequest(
			frac1= calc_pb2.Fraction(num= 1, den = 2),
			frac2= calc_pb2.Fraction(num= 2, den = 3)
		)

		response = stub.Mult(request)
		
		num_res = response.result.num
		den_res = response.result.den
		print(f"Mult result: {num_res} / {den_res}")

if __name__ == '__main__':
	run()

#Fraction(1,2)
#Fraction(2,3)