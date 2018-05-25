echo "Downloading service DTDs..."

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

wget http://dtd.worldpay.com/batchService_v1.dtd    -O $DIR/src/main/resources/dtd/batchService_v1.dtd
wget http://dtd.worldpay.com/paymentService_v1.dtd  -O $DIR/src/main/resources/dtd/paymentService_v1.dtd
