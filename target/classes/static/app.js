// ============================================================
// app.js — shared session helpers, fetch wrapper, UI utils
// ============================================================

// ── Session (sessionStorage) ─────────────────────────────────
function getSession() {
  try { return JSON.parse(sessionStorage.getItem('vrSession') || 'null'); } catch { return null; }
}
function setSession(data) { sessionStorage.setItem('vrSession', JSON.stringify(data)); }
function clearSession()   { sessionStorage.removeItem('vrSession'); }

function requireAdmin() {
  const s = getSession();
  if (!s || s.role !== 'ADMIN') { window.location.href = '/admin-login'; }
  return s;
}
function requireCustomer() {
  const s = getSession();
  if (!s || s.role !== 'CUSTOMER') { window.location.href = '/customer-login'; }
  return s;
}

// ── API fetch wrapper ────────────────────────────────────────
async function apiFetch(url, options = {}) {
  const res = await fetch(url, {
    headers: { 'Content-Type': 'application/json' },
    ...options
  });
  const text = await res.text();
  const data = text ? JSON.parse(text) : null;
  if (!res.ok) throw new Error((data && data.error) || `HTTP ${res.status}`);
  return data;
}

// ── Toast ────────────────────────────────────────────────────
function toast(msg, type = 'success') {
  let c = document.getElementById('toast-container');
  if (!c) { c = document.createElement('div'); c.id = 'toast-container'; document.body.appendChild(c); }
  const t = document.createElement('div');
  t.className = `toast toast-${type}`;
  t.textContent = msg;
  c.appendChild(t);
  setTimeout(() => t.remove(), 3500);
}

// ── Formatters ───────────────────────────────────────────────
function fmtCurrency(n) {
  return '₹' + Number(n).toLocaleString('en-IN', { maximumFractionDigits: 2 });
}
function fmtDate(d) {
  if (!d) return '—';
  return new Date(d + 'T00:00:00').toLocaleDateString('en-IN', { day: '2-digit', month: 'short', year: 'numeric' });
}
function typeIcon(type) { return type === 'CAR' ? '🚗' : '🏍️'; }

function statusBadge(status) {
  const cls = { ACTIVE: 'badge-info', CANCELLED: 'badge-danger', COMPLETED: 'badge-success' };
  return `<span class="badge ${cls[status] || 'badge-amber'}">${status}</span>`;
}
function availBadge(av) {
  return av ? `<span class="badge badge-success">Available</span>`
            : `<span class="badge badge-danger">Booked</span>`;
}

// ── Logout ───────────────────────────────────────────────────
function logout(redirect) {
  clearSession();
  window.location.href = redirect || '/';
}

// ── Topbar user info ─────────────────────────────────────────
function renderTopbar(role) {
  const s = getSession();
  if (!s) return;
  const el = document.getElementById('topbar-user');
 if (el) el.innerHTML = `Logged in as <strong>${s.name || s.email || s.username}</strong>`;
}
