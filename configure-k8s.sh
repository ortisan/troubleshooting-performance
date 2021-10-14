#!/bin/sh

#https://stackoverflow.com/questions/2870992/automatic-exit-from-bash-shell-script-on-error
abort()
{
    echo >&2 '
***************
*** ABORTED ***
***************
'
    echo "An error occurred. Exiting..." >&2
    exit 1
}

trap 'abort' 0

set -e

# Create Cluster
k3d cluster create troubelshooting-cluster --servers 3 --agents 1 --port 9080:80@loadbalancer --port 9443:443@loadbalancer --api-port 6443 --k3s-server-arg '--no-deploy=traefik'

# Update kubeconfig file
k3d kubeconfig merge troubelshooting-cluster --kubeconfig-switch-context

# Create namespace for Istio components
kubectl create namespace istio-system

# Install Istio base chart
helm install istio-base k8s/istio-1.9.9/manifests/charts/base -n istio-system

# Install Istio discovery chart
helm install istiod k8s/istio-1.9.9/manifests/charts/istio-control/istio-discovery -n istio-system

# Install Istio ingress chart
helm install istio-ingress k8s/istio-1.9.9/manifests/charts/gateways/istio-ingress -n istio-system

# Install Istio egress chart
helm install istio-egress k8s/istio-1.9.9/manifests/charts/gateways/istio-egress -n istio-system

# Config istio sidecar injection
kubectl label namespace apps istio-injection=enabled

# Config prometheus
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add kube-state-metrics https://kubernetes.github.io/kube-state-metrics
helm repo update
helm install prometheus prometheus-community/prometheus

# Check installation
kubectl get pods -n istio-system

trap : 0

echo >&2 '
************
*** DONE ***
************
'