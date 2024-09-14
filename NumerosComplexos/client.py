import grpc

# import the generated classes
import calculator_pb2
import calculator_pb2_grpc

# open a gRPC channel
channel = grpc.insecure_channel('localhost:50051')

# create a stub (client)
stub = calculator_pb2_grpc.ComplexCalculatorStub(channel) #number="4.5 - 3.6i" number="0.1 + 0.2i"

# create a valid request message
request = calculator_pb2.ComplexNumberRequest(
    number1= "4.5 - 3.6i",
    number2= "0.1 + 0.2i")

# make the call
response = stub.Soma(request)

# et voilà
print(response.result)