# Check for processes listening on port 8080
output=$(sudo lsof -i :8080 | grep LISTEN)

# If any processes are found, extract the PID and kill it
if [[ -n $output ]]; then
  pid=$(echo $output | awk '{print $2}')
  sudo kill $pid
  echo "Process with PID $pid listening on port 8080 has been terminated."
else
  echo "No processes found listening on port 8080."
fi