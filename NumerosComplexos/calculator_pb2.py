# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# NO CHECKED-IN PROTOBUF GENCODE
# source: calculator.proto
# Protobuf Python Version: 5.27.2
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import runtime_version as _runtime_version
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
_runtime_version.ValidateProtobufRuntimeVersion(
    _runtime_version.Domain.PUBLIC,
    5,
    27,
    2,
    '',
    'calculator.proto'
)
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x10\x63\x61lculator.proto\"8\n\x14\x43omplexNumberRequest\x12\x0f\n\x07number1\x18\x01 \x01(\t\x12\x0f\n\x07number2\x18\x02 \x01(\t\"$\n\x12\x43omplexNumberReply\x12\x0e\n\x06result\x18\x01 \x01(\t2\xfc\x01\n\x11\x43omplexCalculator\x12\x34\n\x04Soma\x12\x15.ComplexNumberRequest\x1a\x13.ComplexNumberReply\"\x00\x12\x39\n\tSubtracao\x12\x15.ComplexNumberRequest\x1a\x13.ComplexNumberReply\"\x00\x12=\n\rMultiplicacao\x12\x15.ComplexNumberRequest\x1a\x13.ComplexNumberReply\"\x00\x12\x37\n\x07\x44ivisao\x12\x15.ComplexNumberRequest\x1a\x13.ComplexNumberReply\"\x00\x62\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'calculator_pb2', _globals)
if not _descriptor._USE_C_DESCRIPTORS:
  DESCRIPTOR._loaded_options = None
  _globals['_COMPLEXNUMBERREQUEST']._serialized_start=20
  _globals['_COMPLEXNUMBERREQUEST']._serialized_end=76
  _globals['_COMPLEXNUMBERREPLY']._serialized_start=78
  _globals['_COMPLEXNUMBERREPLY']._serialized_end=114
  _globals['_COMPLEXCALCULATOR']._serialized_start=117
  _globals['_COMPLEXCALCULATOR']._serialized_end=369
# @@protoc_insertion_point(module_scope)
